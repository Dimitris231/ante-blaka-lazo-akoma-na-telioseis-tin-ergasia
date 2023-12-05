package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Movie {
    private String title;
    private String description;
    private String eligibility;
    private String year;
    private String duration;
    private String category;
    private String characters;

    // Constructors, getters, setters, and any additional methods

    // Example constructor
    public Movie(String title, String description, String eligibility, String year, String duration, String category, String characters) {
        this.title = title;
        this.description = description;
        this.eligibility = eligibility;
        this.year = year;
        this.duration = duration;
        this.category = category;
        this.characters = characters;
    }

    // Example toString method to display movie information
    @Override
    public String toString() {
        return title + '\'' +
                ", description='" + description + '\'' +
                ", eligibility=" + eligibility + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", category=" + category +
                ", characters=" + characters +
                '}';
    }

    // Method to read movie information from a file and save to a HashSet
    public static HashSet<Movie> readFromFileAndSaveToHashSet(String filePath) throws IOException {
        HashSet<Movie> movieSet = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/MA"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Assuming the file format is: title,description,category,stars,eligibility,year,duration
                String title = parts[0].trim();
                String description = parts[1].trim();
                String eligibility = parts[2].trim();
                String year = parts[3].trim();
                String duration = parts[4].trim();
                String category = parts[5].trim();
                String characters = parts[6].trim();

                Movie movie = new Movie(title, description, eligibility, year, duration, category, characters);
                movieSet.add(movie);
            }
        }

        return movieSet;
    }

    public static class Main {
        public static void main(String[] args) throws IOException {
            HashSet<Movie> movieSet = readFromFileAndSaveToHashSet("src/MA");
            for (Movie movie : movieSet) {
                System.out.println(movie);
            }
        }
    }
}




