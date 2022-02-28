package diskerase;

import java.io.File;
import java.nio.file.StandardOpenOption;

import commondev.file.IOUtils;
import commondev.monitoring.Timer;
import commondev.system.ConsoleUtils;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String line = ConsoleUtils.getInstance().readline("Give origin path of file system (example: /home, /tmp )", null);
		
		if(!new File(line).exists()) throw new Exception("origin path doesn't exist");
		
		System.out.println("Erase Disk start");
		Timer timer=new Timer("Erase Disk").start();
		
		// Constant
		String phrase = "SystemErase";
		int loop = 1000000;
		String pathFilesDirectory = new File(line).getAbsolutePath()+"/toDelete/";
		// String fileToCopy=pathFilesDirectory+"source.txt";

		// if doesn't exist, we creating pathFiles
		if (!new File(pathFilesDirectory).exists())
			IOUtils.mkdir(pathFilesDirectory);

		// generation source file
		File createTempFile = IOUtils.createTempFile("source", "txt");
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < loop; i++) {
			stringBuilder.append(phrase);
		}

		IOUtils.saveTextFile(stringBuilder.toString(), createTempFile.getAbsolutePath(), StandardOpenOption.APPEND);

		try {
			boolean x = true;
			int i = 0;
			while (x) {
				IOUtils.fastCopy(createTempFile.getAbsolutePath(),
						pathFilesDirectory + String.format("copy_%s.txt", i));
				i++;
			}
		} catch (Exception e) {
			IOUtils.deleteIfExist(pathFilesDirectory);
		}
		System.out.println(timer.stop().getFormatedTime());
	}

}
