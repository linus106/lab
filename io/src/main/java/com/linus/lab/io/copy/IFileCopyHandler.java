package com.linus.lab.io.copy;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileCopyHandler {

    void copy(String source, String target) throws IOException;

    String getName();
}
