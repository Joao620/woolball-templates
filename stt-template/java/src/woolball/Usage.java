package woolball;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import woolball.WoolBallSpeechToTextService.TranscribeOptions;

public class Usage {

    private static final Logger LOGGER = Logger.getLogger(Usage.class.getName());

    public static void main(String[] args) {
        WoolBallSpeechToTextService wool = new WoolBallSpeechToTextService(WoolBallSpeechToTextService.INSECURE_API_KEY);

        // Default transcription options
        TranscribeOptions options = new WoolBallSpeechToTextService.TranscribeOptions();

        // Transcribes a local audio file
        Path localAudioFile = Paths.get("path/to/your/audiofile.mp3");
        try {
            String transcription = wool.transcribe(localAudioFile, "audio/mpeg", options);
            System.out.println("Transcription of local file: " + transcription);
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error transcribing the local file", e);
        }

        // Transcribes an audio from a URL
        URI audioUrl = URI.create("https://example.com/audiofile.mp3");
        try {
            String transcription = wool.transcribe(audioUrl, new TranscribeOptions());
            System.out.println("Transcription of audio from URL: " + transcription);
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error transcribing the audio from URL", e);
        }

        // Customizing transcription options
        options.withModel("other-model")
               .withLanguage("en")
               .withTimestamps(true)
               .withWebvtt(true);

        // Transcribes the local file with customized options
        try {
            String transcription = wool.transcribe(localAudioFile, "audio/mpeg", options);
            System.out.println("Transcription of local file with customized options: " + transcription);
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error transcribing the local file with customized options", e);
        }
    }
}
