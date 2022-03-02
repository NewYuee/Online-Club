package com.ljy.oneclub.utils;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;

public class DocsUtil {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("F:\\A毕业设计\\基于SSM的学生社团系统的设计与实现\\Code"); // 项目根目录
        config.setProjectName("online-club"); // 项目名称
        config.setApiVersion("V1.1");       // 声明该API的版本
        config.setDocsPath("F:\\A毕业设计\\基于SSM的学生社团系统的设计与实现\\Code\\API"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.FALSE);  // 配置自动生成
        config.addPlugin(new MarkdownDocPlugin());
        Docs.buildHtmlDocs(config); // 执行生成文档

    }
}
