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
    private long videoDuration;
    private boolean isPlayed;
    private boolean isRewind;
    private long rewindTo;
    private String chatMessage;
}
