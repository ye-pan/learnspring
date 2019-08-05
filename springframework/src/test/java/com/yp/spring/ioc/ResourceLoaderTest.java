package com.yp.spring.ioc;

import org.junit.Test;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import static org.junit.Assert.*;

public class ResourceLoaderTest {

    @Test
    public void testDefaultResourceLoader() {
        String location = "C:\\Users\\Administrator\\Documents\\地下城与勇士\\ScreenShot\\ScreenShot2018_0903_222335510.jpg";

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource fileSystemResource = resourceLoader.getResource(location);
        assertTrue(fileSystemResource instanceof ClassPathResource);
        assertFalse(fileSystemResource.exists());

        fileSystemResource = resourceLoader.getResource("file:" + location);
        assertTrue(fileSystemResource instanceof UrlResource);
        assertTrue(fileSystemResource.exists());

        String classpathFile = "sample-beans-xsd.xml";
        Resource classPathResource = resourceLoader.getResource("classpath:" + classpathFile);
        assertTrue(classPathResource instanceof ClassPathResource);

        Resource urlResource = resourceLoader.getResource("https://www.baidu.com");
        assertTrue(urlResource instanceof UrlResource);
        assertTrue(urlResource.exists());
    }

    @Test
    public void testFileSystemResourceLoader() {
        ResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource("F:\\data\\zookeeper\\myid");
        assertTrue(resource instanceof FileSystemResource);
        assertTrue(resource.exists());

        resource = resourceLoader.getResource("file:F:\\data\\zookeeper\\myid");
        assertTrue(resource instanceof UrlResource);
        assertTrue(resource.exists());
    }

    @Test
    public void testResourcePatternResolver() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:sample-appplicationContext.xml");
        assertTrue(resource instanceof  ClassPathResource);

        resolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        resource = resolver.getResource("D:/data/zookeeper/myid");
        assertTrue(resource instanceof FileSystemResource);
    }
}
