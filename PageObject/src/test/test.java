package test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.ExcelUtility.ReadExcel;

public class test {
	public static void main(String[] args) {
		ReadExcel re = new ReadExcel(Paths.get(System.getProperty("user.dir"),"TestData.xlsx").toString());
		String data = re.getData("Sheet1", 1, 1);
		System.out.println("Excel data : "+data);
	}

	static List<Integer> oddNumbers(int l, int r) {
        List<Integer> li = new ArrayList<>();
        if (l % 2 != 0) {
            for (int i = l; i < r; i = i + 2) {
                li.add(i);
            }
            if (r % 2 != 0) {
                li.add(r);
            }
        } else {
            for (int i=l+1; i<r; i=i+2) {
                li.add(i);
            }
            if (r % 2 != 0) {
                li.add(r);
            }
        }
        return li;
    }

	


}
