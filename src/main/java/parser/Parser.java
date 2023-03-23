package parser;

import model.UserInfoModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Parser {

    private static final String URL = "src//main//java//data//cdr.txt";//"C://University//Repositories//CallDataRecord//src//main//java//data//cdr.txt";

    public List<UserInfoModel> findUserInfo(String phoneNumber) throws IOException {
        BufferedReader reader = Files.newBufferedReader(
          Paths.get(URL));
        StringBuilder currentLine = new StringBuilder(reader.readLine());
        List<UserInfoModel> userInfo = new ArrayList<>();

        while(!currentLine.toString().equals("null")) {

            if(phoneNumber.equals(currentLine.substring(4, 15))) {
                String dateFrom = currentLine.substring(17, 31);
                String dateTo = currentLine.substring(33, 47);

                userInfo.add(new UserInfoModel(
                  currentLine.substring(0, 2),
                  dateFrom,
                  dateTo,
                  callTimeCalculation(dateFrom, dateTo),
                  currentLine.substring(49, 51)
                ));
            }

            currentLine.delete(0, currentLine.length());
            currentLine.append(reader.readLine());
        }

        return userInfo;
    }

    private double callTimeCalculation(String stringDateFrom, String stringDateTo) {
        Date dateFrom;
        Date dateTo;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            dateFrom = df.parse(stringDateFrom);
            dateTo = df.parse(stringDateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: ", e);
        }

        return getDateDiff(dateFrom, dateTo);
    }

    private double getDateDiff(Date dateFrom, Date dateTo) {
        long diffInMollies = dateTo.getTime() - dateFrom.getTime();
        return TimeUnit.SECONDS.convert(diffInMollies, TimeUnit.MILLISECONDS);
    }
}
