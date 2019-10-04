package org.ofdrw;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Consumer;


public class TestTool {
    private static final OutputFormat FORMAT = OutputFormat.createPrettyPrint();

    private static final String TEST_DEST = "./target";

    /**
     * 生成XML 并打印打控制台
     * @param name 文件名称
     * @param call 元素添加方法
     */
    public static void genXml(String name, Consumer<Document> call) {
        Document doc = DocumentHelper.createDocument();
        String filePath = TEST_DEST + File.separator + name + ".xml";
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            call.accept(doc);

            // 格式化打印到控制台
            XMLWriter writerToSout = new XMLWriter(System.out, FORMAT);
            writerToSout.write(doc);

            // 打印打到文件
            XMLWriter writeToFile = new XMLWriter(out, FORMAT);
            writeToFile.write(doc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 加入待测试元素，生成XML 并打印打控制台
     * @param name 文件名称
     * @param element 元素
     */
    public static void genXml(String name, Element element) {
        genXml(name, doc -> doc.add(element));
    }
}