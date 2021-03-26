package uk.co.mruoc.transport;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class DirectoryDeleter {

    private DirectoryDeleter() {
        // utility class
    }

    public static void delete(String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
