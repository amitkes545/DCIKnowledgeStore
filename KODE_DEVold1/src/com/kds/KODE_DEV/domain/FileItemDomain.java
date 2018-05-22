package com.kds.KODE_DEV.domain;

import java.io.File;

import org.apache.commons.fileupload.FileItem;

public class FileItemDomain {
	private File file;
	
	private String item1;

	private String item2;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getItem1() {
		return item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public String getItem2() {
		return item2;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}
}
