package com.zhiyou100.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class Hadoop01 {
    FileSystem fs=null;
    @Before
    public void init() throws IOException {
        Configuration conf = new Configuration();
        fs=FileSystem.get(conf);

    }

    @Test
    public void testUpLoad() throws IOException {
        fs.copyFromLocalFile(new Path("C://Users//user//Desktop//service"),new Path("/service.log"));
        fs.close();
    }

}
