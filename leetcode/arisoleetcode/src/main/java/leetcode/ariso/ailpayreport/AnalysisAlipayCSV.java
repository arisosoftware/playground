package leetcode.ariso.ailpayreport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;

public class AnalysisAlipayCSV {

	public void Parse(String CSVFileName) throws Exception {

		// BufferedReader reader = new BufferedReader(new FileReader(CSVFileName ));
		// BufferedReader reader = Files.newBufferedReader(CSVFileName,
		// StandardCharsets.UTF_8);

		BufferedReader reader = null;
		try {
			String charset = "GBK";
		//	charset ="windows-1252"; charset ="ISO-8859-1";String charset = "UTF-8";
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(CSVFileName), charset));
 
			String line1;
			int maxLine = 100;
			while ((line1 = reader.readLine()) != null) {
				maxLine--;
				if (line1.indexOf("交易记录明细列表") > 0)
					break;
				if (maxLine < 0)
					throw new Exception("invaild file format, may the file is not alipay report?");
			}

			CsvReader csvReader = new CsvReader();
			csvReader.setContainsHeader(true);

			CsvContainer csv = csvReader.read(reader);
			for (CsvRow row : csv.getRows()) {
				System.out.println("Read line: " + row);
				System.out.println("First column of line: " + row.getField(0));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (reader != null)
				reader.close();
		}

	}
}
