package ec.com.andresguachisaca.SriConsultas;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

public class UtilsExcel {

	private static HSSFCellStyle styleStringCabecera;
	private static HSSFCellStyle styleString;
	private static HSSFCellStyle styleInteger;
	private static HSSFCellStyle styleDecimal;
	private static HSSFCellStyle styleDecimalSD;
	private static HSSFCellStyle styleDecimalSF;

	public static String comprobarNumericoString(String dato) {
		try {
			return "D" + new BigDecimal(dato) + "¬";
		} catch (Exception e) {
			return "S" + dato + "¬";
		}
	}

	public static String crearExcel(List<String> listaCabecera, List<String> listaCuerpo, String namesheet,
			String nombreXLS) {
		try {

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(namesheet);
			styleStringCabecera = wb.createCellStyle();
			styleString = wb.createCellStyle();
			styleInteger = wb.createCellStyle();
			styleDecimal = wb.createCellStyle();
			styleDecimalSD = wb.createCellStyle();
			styleDecimalSF = wb.createCellStyle();
			HSSFFont fontCabecera = wb.createFont();
			HSSFFont font = wb.createFont();
			HSSFRow row;
			for (int i = 0; i < listaCabecera.size() + listaCuerpo.size(); i++) {
				String fila = "";
				if (i < listaCabecera.size()) {
					fila = listaCabecera.get(i);
				} else {
					fila = listaCuerpo.get(i - listaCabecera.size());
				}
				StringTokenizer st = new StringTokenizer(fila, "¬");
				row = sheet.createRow((short) i);

				int j = 0;
				while (st.hasMoreTokens()) {
					String token = st.nextToken();
					if (i <= listaCabecera.size()) {
						styleStringCabecera = wb.createCellStyle();
						fontCabecera = wb.createFont();
						fontCabecera.setFontHeightInPoints((short) 8);
						fontCabecera.setFontName("Arial");
						fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						if (i == 0) {
							fontCabecera.setFontHeightInPoints((short) 12);
							fontCabecera.setColor(IndexedColors.AUTOMATIC.getIndex());
						}
						styleStringCabecera.setFont(fontCabecera);
						createCell(row, j, token, styleStringCabecera);
					}
					if (i == listaCabecera.size()) {
						styleStringCabecera = wb.createCellStyle();
						fontCabecera = wb.createFont();
						fontCabecera.setFontHeightInPoints((short) 10);
						fontCabecera.setFontName("Arial");
						fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						fontCabecera.setColor(IndexedColors.AUTOMATIC.getIndex());
						styleStringCabecera.setFont(fontCabecera);
						createCell(row, j, token, styleStringCabecera);
					}
					if (i > listaCabecera.size()) {
						font.setFontHeightInPoints((short) 8);
						font.setFontName("Arial");
						font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
						font.setColor(IndexedColors.AUTOMATIC.getIndex());
						if (token.charAt(0) == 'S') {
							styleString.setFont(font);
							createCell(row, j, token, styleString);
						} else if (token.charAt(0) == 'I') {
							styleInteger.setFont(font);
							createCell(row, j, token, styleInteger);
						} else if (token.charAt(0) == 'D') {
							styleDecimal.setFont(font);
							createCell(row, j, token, styleDecimal);
						} else if (token.charAt(0) == 'C') {
							styleDecimalSD.setFont(font);
							createCell(row, j, token, styleDecimalSD);
						} else if (token.charAt(0) == 'F') {
							styleDecimalSF.setFont(font);
							createCell(row, j, token, styleDecimalSF);
						}
					}
					j = j + 1;
				}
			}

			for (int i = 1; i < listaCabecera.size() + listaCuerpo.size(); i++) {
				sheet.autoSizeColumn((short) i);
			}

			FileOutputStream fileOut = new FileOutputStream(nombreXLS + ".xls");
			wb.write(fileOut);
			fileOut.close();
			return nombreXLS + ".xls";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void createCell(HSSFRow row, int i, String value, HSSFCellStyle style) {
		HSSFCell cell = row.createCell(i);
		value = value + " ";
		if ((value.charAt(0) == 'I' || value.charAt(0) == 'D' || value.charAt(0) == 'C' || value.charAt(0) == 'F')
				&& !value.substring(1).equals(" ")) {
			if (value.charAt(0) == 'I') {
				style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
			} else if (value.charAt(0) == 'D') {
				style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
			} else if (value.charAt(0) == 'C') {
				style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
			} else if (value.charAt(0) == 'F') {
				style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
			}
			cell.setCellValue(Double.parseDouble(value.substring(1).trim()));
		} else {
			cell.setCellValue(value.substring(1).trim());
		}
		if (value.charAt(0) == 'S') {
			style.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
			cell.setCellValue(new HSSFRichTextString(value.substring(1).trim()));
		}
		cell.setCellStyle(style);
	}

}