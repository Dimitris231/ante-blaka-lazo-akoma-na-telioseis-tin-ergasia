package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Episode {
    private String episodeTitle;
    private String duration; // in minutes

    public Episode(String episodeTitle, String duration) {
        this.episodeTitle = episodeTitle;
        this.duration = duration;
    }

    // Getters and setters for Classes.Episode class
    // ...

    @Override
    public String toString() {
        return "Classes.Episode{" +
                "episodeTitle='" + episodeTitle + '\'' +
                ", duration=" + duration +
                '}';
    }
}

class Season {
    private String seasonNumber;
    private String year;
    private List<Episode> episodes;

    public Season(String seasonNumber, String year, List<Episode> episodes) {
        this.seasonNumber = seasonNumber;
        this.year = year;
        this.episodes = episodes;
    }

    // Getters and setters for Classes.Season class
    // ...

    @Override
    public String toString() {
        return "Classes.Season{" +
                "seasonNumber=" + seasonNumber +
                ", year=" + year +
                ", episodes=" + episodes +
                '}';
    }
}

public class Series {
    private String title;
    private String description;
    private String eligibility;
    private String category;
    private List<String> actors;
    private List<Season> seasons;

    // Constructors, getters, setters, and any additional methods

    // Example constructor
    public Series(String title, String description, String eligibility, String category, List<String> actors, List<Season> seasons) {
        this.title = title;
        this.description = description;
        this.eligibility = eligibility;
        this.category = category;
        this.actors = actors;
        this.seasons = seasons;
    }

    // Getters and setters for Classes.Series class
    // ...

    @Override
    public String toString() {
        return "Classes.Series{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", eligibility=" + eligibility +
                ", category='" + category + '\'' +
                ", actors=" + actors +
                ", seasons=" + seasons +
                '}';
    }

    // Method to read series information from a file and save to a HashSet
    public static HashSet<Series> readFromFileAndSaveToHashSet(String filePath) throws IOException {
        HashSet<Series> seriesSet = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Assuming the file format is: title,description,eligibility,category,actor1;actor2;...,season1;year1:episode1:duration;episode2:duration;...,season2;year2:episode1:duration;episode2:duration;...
                String title = parts[0].trim();
                String description = parts[1].trim();
                String eligibility = parts[2].trim();
                String category = parts[3].trim();
                List<String> actors = new LinkedList<>(List.of(parts[4].split(";"))); // Split actors by semicolon
                List<Season> seasons = new LinkedList<>();

                // Process seasons
                for (int i = 5; i < parts.length; i++) {
                    String[] seasonData = parts[i].split(";");
                    String seasonNumber = seasonData[0];
                    String year = seasonData[1];
                    List<Episode> episodes = new LinkedList<>();

                    // Process episodes within the season
                    for (int j = 2; j < seasonData.length; j++) {
                        String[] episodeData = seasonData[j].split(":");
                        String episodeTitle = episodeData[0].trim();
                        String duration = episodeData[1].trim();
                        episodes.add(new Episode(episodeTitle, duration));
                    }

                    seasons.add(new Season(seasonNumber, year, episodes));
                }

                // Create a new series and add it to the HashSet
                Series series = new Series(title, description, eligibility, category, actors, seasons);
                seriesSet.add(series);
            }
        }

        return seriesSet;
    }

    public static void test(String[] args) {
        try {
            // Example file path, replace with your actual file path
            String filePath = "src/FF";

            // Read series information from the file and save to a HashSet
            HashSet<Series> seriesSet = Series.readFromFileAndSaveToHashSet(filePath);

            // Display series information
            for (Series series : seriesSet) {
                System.out.println(series);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
