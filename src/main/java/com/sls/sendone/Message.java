package com.sls.sendone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sls
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String userId;

    private String message;
}
