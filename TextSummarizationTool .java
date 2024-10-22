import java.util.*;

public class TextSummarizationTool {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text you want to summarize (end with a blank line):");

        StringBuilder inputText = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            inputText.append(line).append(" ");
        }

        // Count the number of words
        int wordCount = countWords(inputText.toString());
        System.out.println("Word Count: " + wordCount);

        // Summarize the text
        String summary = summarizeText(inputText.toString(), 3); // Summarize to 3 sentences
        System.out.println("\nSummary:");
        System.out.println(summary);

        scanner.close();
    }

    // Function to count the number of words
    private static int countWords(String text) {
        String[] words = text.trim().split("\\s+"); // Split the text into words based on whitespace
        return words.length; // Return the word count
    }

    // Function to summarize the text
    public static String summarizeText(String text, int numSentences) {
        String[] sentences = text.split("\\. ");
        Map<String, Integer> wordFrequency = new HashMap<>();

        // Calculate word frequency
        for (String sentence : sentences) {
            String[] words = sentence.toLowerCase().replaceAll("[^a-z]", "").split("\\s+");
            for (String word : words) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        // Score sentences based on word frequency
        Map<String, Integer> sentenceScores = new HashMap<>();
        for (String sentence : sentences) {
            int score = 0;
            String[] words = sentence.toLowerCase().replaceAll("[^a-z]", "").split("\\s+");
            for (String word : words) {
                score += wordFrequency.getOrDefault(word, 0);
            }
            sentenceScores.put(sentence, score);
        }

        // Sort sentences by score
        List<Map.Entry<String, Integer>> sortedSentences = new ArrayList<>(sentenceScores.entrySet());
        sortedSentences.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Get the top 'numSentences' sentences
        StringBuilder summary = new StringBuilder();
        for (int i = 0; i < Math.min(numSentences, sortedSentences.size()); i++) {
            summary.append(sortedSentences.get(i).getKey()).append(". ");
        }

        return summary.toString().trim();
    }
}
