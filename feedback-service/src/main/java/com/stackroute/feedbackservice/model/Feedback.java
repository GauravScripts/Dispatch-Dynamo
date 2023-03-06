package com.stackroute.feedbackservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collation = "feedback")
public class Feedback {
    @Id
    String serviceBookingId;
    String userName;
    String userEmailId;
    String review;
    String suggestion;
}
