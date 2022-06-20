package com.education.mkh.trees.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WritingInFile implements Observer{

	@Override
	public void update(TreeFunctionable tree) {
		List list = tree.toList();
		Runnable r = () -> {
			File path = new File("C:\\Users\\User\\Desktop\\result.txt");
			FileWriter wr;
			try {
				wr = new FileWriter(path);
				wr.write(list.toString());
				wr.flush();
				wr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		new Thread(r).start();
	}

}
