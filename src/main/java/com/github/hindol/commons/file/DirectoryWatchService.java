package com.github.hindol.commons.file;

import com.github.hindol.commons.core.Service;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Interface definition of a simple directory watch service.
 *
 * Implementations of this interface allow interested parties to <em>listen</em>
 * to file system events coming from a specific directory.
 */
public interface DirectoryWatchService extends Service {

    @Override
    void start() /* throws Exception */;

    /**
     * Notifies the implementation of <em>this</em> interface that <code>dirPath</code>
     * should be monitored for file system events. If the changed file matches any
     * of the <code>globPatterns</code>, <code>listener</code> should be notified.
     *
     * @param listener The listener.
     * @param dirPath The directory path.
     * @param globPatterns Zero or more file patterns to be matched against file names.
     *                     If none provided, matches <em>any</em> file.
     * @throws IOException If <code>dirPath</code> is not a directory.
     */
    void register(OnFileChangeListener listener, String dirPath, String... globPatterns)
            throws IOException;

    /**
     * Interface definition for a callback to be invoked when a file under
     * watch is changed.
     */
    interface OnFileChangeListener {

        /**
         * Called when the file is created.
         * @param fileName The file name.
         */
        default void onFileCreate(String fileName) {}

        /**
         * Called when the file is modified.
         * @param fileName The file name.
         */
        default void onFileModify(String fileName) {}

        /**
         * Called when the file is deleted.
         * @param fileName The file name.
         */
        default void onFileDelete(String fileName) {}
    }
}
