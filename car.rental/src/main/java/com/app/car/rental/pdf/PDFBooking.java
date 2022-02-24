package com.app.car.rental.pdf;

import com.app.car.rental.model.Booking;
import com.app.car.rental.model.Prices;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PDFBooking {

    public void createPdfBooking(Booking booking){

        long diff = getDiffrentInDates(booking.getStartDate(), booking.getEndDate());
        double discount = getRightDiscount(booking.getCar().getPrice(), diff);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = dateFormat.format(booking.getStartDate());
        String endDateString = dateFormat.format(booking.getEndDate());




        Document document = new Document(PageSize.LETTER.rotate());
        String title = String.valueOf(booking.getBookingId() + "-" + booking.getCar().getId() + booking.getClient().getId() + String.valueOf(booking.getStartDate().getTime())) + ".pdf";

        String[] headerBookingDetails = new String[] {"Numer zamowienia: ", "Data poczatku wynajmu: ", "Data zakonczenia wynajmu: ", "Calkowity koszt wynajmu: "};
        String[] rowBookingDetails = new String[] {String.valueOf(booking.getBookingId()), startDateString, endDateString, String.valueOf(booking.getFinalPrice()) + " PLN"};

        String[] headerClientDetails = new String[] {"Numer klineta: ", "Adres mailowy: ", "Imię kierowcy: ", "Nazwisko: ", "Numer telefonu: "};
        String[] rowClientDetails = new String[] {String.valueOf(booking.getClient().getId()), String.valueOf(booking.getClient().getEmail()),
                String.valueOf(booking.getClient().getFirstname()), String.valueOf(booking.getClient().getLastname()), booking.getClient().getPhone()};

        String[] headerClientAddressDetails = new String[] {"Ulica: ", "Numer domu/lokalu: ", "Miasto"};
        String[] rowClientAddressDetails = new String[] {String.valueOf(booking.getClient().getStreet()), String.valueOf(booking.getClient().getNumber()),
                String.valueOf(booking.getClient().getCity())};

        String[] headerCarDetails = new String[] { "Model: ", "Marka: ", "Rok produkcji: ", "Nadwozie: ", "Silnik: ", "Skrzynia: "};
        String[] rowCarDetails = new String[] { String.valueOf(booking.getCar().getModel()),
                String.valueOf(booking.getCar().getBrand()), String.valueOf(booking.getCar().getProductionYear()), String.valueOf(booking.getCar().getBody()),
                String.valueOf(booking.getCar().getEngine()), String.valueOf(booking.getCar().getGearbox())};

        String[] headerCarEquDetails = new String[] {"Kolor: ", "GPS: ", "Liczba pasazerów: ", "Liczba drzwi: ", "Klimatyzacja: "};
        String[] rowCarEquDetails = new String[] {String.valueOf(booking.getCar().getColor()), String.valueOf(booking.getCar().getGps()),
                String.valueOf(booking.getCar().getNumberPassengers()), String.valueOf(booking.getCar().getDoors()), String.valueOf(booking.getCar().getAirConditioning())};

        String[] headerPriceDetails = new String[] {"Cena", "Rabat", "Wartosc", "Ilosc", "Wartosc zamowienia"};
        String[] rowPriceDetails = new String[] {String.valueOf(String.valueOf(getPricePerDayorMonth(booking.getCar().getPrice(), diff))) + " PLN",
                getCorrectDiscount(booking.getCar().getPrice(), diff), String.valueOf(getPriceAfterDiscount(booking.getCar().getPrice(), diff)) + " PLN",
                String.valueOf(diff), String.valueOf(getFinalPricePerDayAfterDiscount(booking.getCar().getPrice(), diff)) + " PLN"};


        try{
            PdfWriter.getInstance(document, new FileOutputStream("PDF/" + title));
            document.open();
            document.setPageCount(1);

            //Adding text into pdf file
            Font fontHeaderOfDocument = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD & Font.UNDERLINE);
            Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font fontRow = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
            Font fontSubParagraph = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD & Font.UNDERLINE);
            document.addTitle(title);

            Paragraph headerParagraph = new Paragraph("Rezerwacja numer: " + booking.getBookingId(), fontHeaderOfDocument);
            headerParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(headerParagraph);
            document.add(Chunk.NEWLINE);
            PdfPTable tableBookingDetails = new PdfPTable(headerBookingDetails.length);
            for(String header: headerBookingDetails){
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.5f);
                cell.setBackgroundColor(BaseColor.GREEN);
                cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
                tableBookingDetails.addCell(cell);
            }

            tableBookingDetails.completeRow();

            for(String row: rowBookingDetails){
                Phrase phrase = new Phrase(row, fontRow);
                tableBookingDetails.addCell(new PdfPCell(phrase));
            }

            tableBookingDetails.completeRow();

            document.add(tableBookingDetails);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            PdfPTable tablePriceDetails = new PdfPTable(headerPriceDetails.length);
            for(String header: headerPriceDetails){
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.5f);
                cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
                tablePriceDetails.addCell(cell);
            }

            tablePriceDetails.completeRow();

            for(String row: rowPriceDetails){
                Phrase phrase = new Phrase(row, fontRow);
                tablePriceDetails.addCell(new PdfPCell(phrase));
            }

            tablePriceDetails.completeRow();
            document.add(tablePriceDetails);


            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);


            Paragraph subParagraph = new Paragraph("Podsumowanie:                        TOTAL: " +
                    String.valueOf(getFinalPricePerDayAfterDiscount(booking.getCar().getPrice(), diff)) + " PLN", fontSubParagraph);
            subParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(subParagraph);
            document.newPage();

            document.add(Chunk.NEWLINE);

            PdfPTable tableCarDetails = new PdfPTable(headerCarDetails.length);
            for(String header: headerCarDetails){
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.5f);
                cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
                tableCarDetails.addCell(cell);
            }

            tableCarDetails.completeRow();

            for(String row: rowCarDetails){
                Phrase phrase = new Phrase(row, fontRow);
                tableCarDetails.addCell(new PdfPCell(phrase));
            }

            tableCarDetails.completeRow();
            document.add(tableCarDetails);

            document.add(Chunk.NEWLINE);


            PdfPTable tableCarEquipmentDetails = new PdfPTable(headerCarEquDetails.length);
            for (String header: headerCarEquDetails){
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.5f);
                cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
                tableCarEquipmentDetails.addCell(cell);
            }

            tableCarEquipmentDetails.completeRow();

            for(String row: rowCarEquDetails){
                Phrase phrase = new Phrase(row, fontRow);
                tableCarEquipmentDetails.addCell(new PdfPCell(phrase));
            }

            tableCarEquipmentDetails.completeRow();
            document.add(tableCarEquipmentDetails);

            document.add(Chunk.NEWLINE);

            PdfPTable tableClientDetails = new PdfPTable(headerClientDetails.length);
            for(String header: headerClientDetails){
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.5f);
                cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
                tableClientDetails.addCell(cell);
            }

            tableClientDetails.completeRow();

            for(String row: rowClientDetails){
                Phrase phrase = new Phrase(row, fontRow);
                tableClientDetails.addCell(new PdfPCell(phrase));
            }

            tableClientDetails.completeRow();
            document.add(tableClientDetails);

            document.add(Chunk.NEWLINE);

            PdfPTable tableClientAddressDetails = new PdfPTable(headerClientAddressDetails.length);
            for (String header: headerClientAddressDetails){
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.5f);
                cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
                tableClientAddressDetails.addCell(cell);
            }

            tableClientAddressDetails.completeRow();

            for(String row: rowClientAddressDetails){
                Phrase phrase = new Phrase(row, fontRow);
                tableClientAddressDetails.addCell(new PdfPCell(phrase));
            }

            tableClientAddressDetails.completeRow();
            document.add(tableClientAddressDetails);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph paragraphEnd = new Paragraph("W przypadku pytan, zapraszamy do kontaktu z naszą infolinią 24/7 pod numerem: \n" +
                    "800 999 999 z telefonów stacjonarnych lub 22 33 11 448 z telefonów komórkowych !");

            paragraphEnd.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraphEnd);

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    public long getDiffrentInDates(Date startDate, Date endDate){
        long diff = endDate.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public double getPricePerDayorMonth(Prices price, long days){
        double p;
        if(days >=28){
            p = price.getPricePerMonth();
        }else {
            p = price.getPricePerDay();
        }
        return p;
    }

    public double getPriceAfterDiscount(Prices price, long days){
        double discount = getRightDiscount(price, days);
        double priceAfterDiscount;
        if(days >= 28){
            priceAfterDiscount = price.getPricePerMonth();
        }else{
            priceAfterDiscount = price.getPricePerDay() * discount;
        }
        return priceAfterDiscount;
    }

    public String getCorrectDiscount(Prices prices, long days){
        double discount = 0;
        if(days > 7 && days < 28){
            discount = prices.getDiscountAfterOneWeek() * 100;
        }else if(days > 3 && days <=7) {
            discount = prices.getDiscountAfterThreeDays();
        }else {
            discount = 0;
        }

        return String.valueOf(discount) + " %";
    }

    public double getFinalPricePerDayAfterDiscount(Prices prices, long days){
        double finalPrice;
        int counter = 0;
        if(days >= 28){
            if(days % 28 == 0 || days % 29 == 0 || days % 30 == 0 || days % 31 == 0 ){
                finalPrice = prices.getPricePerMonth();
            }else{
                counter = (int)days / 30;
                counter++;
                finalPrice = prices.getPricePerMonth() * counter;
            }
        }else if(days > 7 && days < 28){
            finalPrice = prices.getPricePerDay() * prices.getDiscountAfterOneWeek() * days;
        }else if(days <=7 && days > 3){
            finalPrice = prices.getPricePerDay() * prices.getDiscountAfterThreeDays() * days;
        }else{
            finalPrice = prices.getPricePerDay() * days;
        }

        return finalPrice;
    }

    /*public String getDiscountFormat(double discountDoubleFormat){
        double discount = discountDoubleFormat * 100;
        return String.valueOf(discount) + " %";
    }*/

    public double getRightDiscount(Prices prices, long diff) {
        if (diff < 3) {
            return 0.0;
        } else if (diff >= 3 && diff <= 7) {
            return prices.getDiscountAfterThreeDays();
        } else if (diff > 7 && diff < 28) {
            return prices.getDiscountAfterOneWeek();
        } else return 0.0;
    }

    public String getFileName(String id){
        File dir = new File("PDF/");
        String fileName = "";
        FilenameFilter filter = new FilenameFilter() {


            @Override
            public boolean accept(File dir, String name) {

                return name.startsWith(id);
            }
        };

        String[] children = dir.list(filter);
        if(children.length == 1) {

            for(int i = 0; i<children.length; i++) {
                fileName = children[i];
            }
        }else {
            System.out.println("Something się spierdoliło...");
        }

        return fileName;

    }

}
