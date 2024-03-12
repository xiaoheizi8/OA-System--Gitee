package cap.ljw.util;

import cap.ljw.annotation.ExcelColumn;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Ljw
 * 2023.7.13
 * 数据写入excel
 */
public class HutoolExcelUtil {
//         如果设置了别名，就只写入设置了别名的列；否则按字段名，默认全部输出
  public   static <T> void writeExcel(HttpServletResponse response, List<T> list,String filename,Class<T>clazz) throws IOException{
        //统计列数
        Integer columnNum=0;
        //初始化excel对象
        ExcelWriter writer= ExcelUtil.getWriter(true);
        //判断序列号
        boolean isSerializable=false;
        //这里写法就像用序列号数字自动生成时候一样,思维是逆向的
        Field[] fields=clazz.getDeclaredFields();
        for (Field field :fields){
            //only 私有变量可访问
            field.setAccessible(true);
            if (field.getName().equals("serialVersionUID")){
                isSerializable=true;
            }if (field.isAnnotationPresent(ExcelColumn.class)){
                ExcelColumn excelColumn=field.getAnnotation(ExcelColumn.class);
                //增加标题名
            writer.addHeaderAlias(field.getName(),excelColumn.value());
            columnNum++;

            }
        }
        //如果未设置别名,原型输出
        if (columnNum==0) {
            if (isSerializable) {
                //此部分列索引将要合并的最后一列索引，不包含合并列数
                writer.merge(fields.length - 2, filename);
            } else {
                writer.merge(fields.length - 1, filename);//根据实体类的序列号ID
            }
        }else {
                writer.setOnlyAlias(true);//当别名字段写存储可以调用
                writer.merge(columnNum-1,filename);//sheet标题
            }
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        // 设置浏览器响应的格式
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        // 关闭writer，释放内存
        writer.close();
        //关闭输出流
        IoUtil.close(outputStream);
        }
        //读取excel数据
        public static <T> List<T> readExcel(InputStream inputStream, Integer headerRowIndex, Class<T> clazz) {
            ExcelReader reader = ExcelUtil.getReader(inputStream);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 设置私有变量可访问
                field.setAccessible(true);
                if (field.isAnnotationPresent(ExcelColumn.class)) {
                    ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                    reader.addHeaderAlias(excelColumn.value(), field.getName());
                }
            }
            // 从起始行读取数据
            List<T> list = reader.read(headerRowIndex, headerRowIndex + 1, Integer.MAX_VALUE, clazz);
            reader.close();
            return list;
        }

}
