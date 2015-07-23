package com.honey.utils;

import java.io.File;
import java.util.Collection;

public class FileUtils {
	public static void listFiles(File file, int fileLevel,String filterName,
			Collection<File> files) {
		if (file.isDirectory()) {
			String[] str = file.list();
			for (int i = 0; i < str.length; i++) {
				listFiles(new File(file.getPath() + "\\" + str[i]),
						fileLevel + 1, filterName,files);
			}
		} else {
			if (file.getName().contains(filterName)) {
				files.add(file);
			}

		}
	}

	public static Collection<File> listFiles(File file,String filterName) {
		Collection<File> files = new java.util.LinkedList<File>();
		listFiles(file, 0,filterName, files);
		return files;
	}

	public String getPath(File mFile) {
		String fullPath = mFile.getPath();
		String[] str = fullPath.split("\\\\");
		return str[str.length - 1];
	}

}
