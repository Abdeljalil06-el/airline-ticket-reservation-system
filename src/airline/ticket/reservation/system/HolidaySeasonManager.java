package airline.ticket.reservation.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class HolidaySeasonManager {
    private Set<LocalDate> publicHolidays = new HashSet<>();
    private List<DateRange> schoolVacations = new ArrayList<>();
    private List<DateRange> peakSeasons = new ArrayList<>();
    private List<DateRange> highSeasons = new ArrayList<>();
    private List<DateRange> offPeakSeasons = new ArrayList<>();
    private List<DateRange> regularSeasons = new ArrayList<>();

    // Load combined CSV file
    public void loadFromCSV(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // skip header
            LocalDate currentSchoolVacationStart = null;
            String currentSchoolVacationName = null;
            LocalDate currentSeasonStart = null;
            String currentSeasonName = null;
            String currentSeasonNotes = null;

            List<LocalDate> offPeakDates = new ArrayList<>();
            List<LocalDate> peakDates = new ArrayList<>();
            List<LocalDate> highDates = new ArrayList<>();
            List<LocalDate> regularDates = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length < 4) continue;

                LocalDate date;
                try {
                    date = LocalDate.parse(parts[0].trim());
                } catch (Exception e) {
                    continue;
                }

                String type = parts[1].trim();
                String name = parts[2].trim();
                String notes = parts[3].trim();

                switch (type) {
                    case "Public Holiday":
                        publicHolidays.add(date);
                        break;

                    case "School Holiday":
                        if (name.toLowerCase().contains("start")) {
                            currentSchoolVacationStart = date;
                            currentSchoolVacationName = name.replace(" Start", "");
                        } else if (name.toLowerCase().contains("end") && currentSchoolVacationStart != null) {
                            schoolVacations.add(new DateRange(currentSchoolVacationStart, date, currentSchoolVacationName));
                            currentSchoolVacationStart = null;
                            currentSchoolVacationName = null;
                        }
                        break;

                    case "Season":
                        if (name.toLowerCase().contains("start")) {
                            currentSeasonStart = date;
                            currentSeasonName = name.replace(" Start", "");
                            currentSeasonNotes = notes;
                        } else if (name.toLowerCase().contains("end") && currentSeasonStart != null) {
                            DateRange dr = new DateRange(currentSeasonStart, date, currentSeasonNotes);
                            switch (currentSeasonNotes.toLowerCase()) {
                                case "peak":
                                    peakSeasons.add(dr);
                                    break;
                                case "high":
                                    highSeasons.add(dr);
                                    break;
                                case "off-peak":
                                    offPeakSeasons.add(dr);
                                    break;
                                case "regular":
                                    regularSeasons.add(dr);
                                    break;
                            }
                            currentSeasonStart = null;
                            currentSeasonName = null;
                            currentSeasonNotes = null;
                        }
                        break;

                    case "Inferred":  // Support inferred daily Off-Peak or other season markers
                        switch (notes.toLowerCase()) {
                            case "off-peak":
                                offPeakDates.add(date);
                                break;
                            case "peak":
                                peakDates.add(date);
                                break;
                            case "high":
                                highDates.add(date);
                                break;
                            case "regular":
                                regularDates.add(date);
                                break;
                        }
                        break;
                }
            }

            // Group inferred season days into ranges
            groupIntoRanges(offPeakDates, offPeakSeasons, "Off-Peak");
            groupIntoRanges(peakDates, peakSeasons, "Peak");
            groupIntoRanges(highDates, highSeasons, "High");
            groupIntoRanges(regularDates, regularSeasons, "Regular");
        }
    }

    // Utility: Group consecutive dates into a range
    private void groupIntoRanges(List<LocalDate> dates, List<DateRange> ranges, String label) {
        Collections.sort(dates);
        LocalDate start = null, prev = null;

        for (LocalDate date : dates) {
            if (start == null) {
                start = date;
                prev = date;
            } else if (date.equals(prev.plusDays(1))) {
                prev = date;
            } else {
                ranges.add(new DateRange(start, prev, label));
                start = date;
                prev = date;
            }
        }

        if (start != null) {
            ranges.add(new DateRange(start, prev, label));
        }
    }

    public boolean isPublicHoliday(LocalDate date) {
        return publicHolidays.contains(date);
    }

    public boolean isSchoolHoliday(LocalDate date) {
        for (DateRange dr : schoolVacations) {
            if (dr.contains(date)) return true;
        }
        return false;
    }

    public String getSeason(LocalDate date) {
        for (DateRange dr : peakSeasons) {
            if (dr.contains(date)) return "Peak";
        }
        for (DateRange dr : highSeasons) {
            if (dr.contains(date)) return "High";
        }
        for (DateRange dr : offPeakSeasons) {
            if (dr.contains(date)) return "Off-Peak";
        }
        for (DateRange dr : regularSeasons) {
            if (dr.contains(date)) return "Regular";
        }
        return "Regular";
    }

    public static class DateRange {
        LocalDate start;
        LocalDate end;
        String description;

        public DateRange(LocalDate start, LocalDate end, String description) {
            this.start = start;
            this.end = end;
            this.description = description;
        }

        public boolean contains(LocalDate date) {
            return (date.isEqual(start) || date.isAfter(start)) &&
                   (date.isEqual(end) || date.isBefore(end));
        }
    }
}
