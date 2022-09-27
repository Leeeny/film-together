package leeny.edu.json;

import leeny.edu.enums.ClientStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ResponseJSON {
    private String username;
    private ClientStatus clientStatus;
    private boolean isConnected;
    private boolean isUploaded;
    private double videoDuration;
    private boolean isPlayed;
    private boolean isRewind;
    private double rewindTo;
    private String chatMessage;
}
