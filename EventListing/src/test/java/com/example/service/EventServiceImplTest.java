package com.example.service;
 
import org.junit.jupiter.api.Assertions;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
 
import com.example.model.EventDetails;
import com.example.repo.EventRepo;
 
@SpringBootTest
public class EventServiceImplTest {
 
	    @MockBean
	    private EventRepo repo;
	    @Autowired
	    private EventService service;
 
	    @BeforeEach
	    public void setUp() {
	    	EventDetails e = new EventDetails(0, LocalDate.parse("2020-12-12"), "music", "concert", "Mumbai", "1000", "5");
	    	List<EventDetails> events = new ArrayList<>();
	        events.add(e);
	    	Mockito.when(repo.save(e)).thenReturn(e);
	    	Mockito.when(repo.findById(0)).thenReturn(Optional.of(e));
	    	Mockito.when(repo.findByEventType("music")).thenReturn(events);
	    }
 
	    @Test
	    public void testAddEvent() {
	        EventDetails event = new EventDetails();
	        event.setEventName("concert");
	        ResponseEntity<?> response = service.addEvent(event);
	        Assertions.assertEquals("Event Test Event added successfully.", response.getBody());
	    }
 
 
//	    @Test
//	    public void testGetEventById() {
//	        EventDetails event = new EventDetails();
//	        event.setEventId(0);
//	        ResponseEntity<?> response = service.getEventById(0);
//	        Assertions.assertEquals(event, response.getBody());
//	    }
//
//	    @Test
//	    public void testGetEventByType() {
//	        EventDetails event = new EventDetails();
//	        List<EventDetails> events = new ArrayList<>();
//	        events.add(event);
//	        event.setEventType("music");
//	        ResponseEntity<?> response = service.getEventByType("music");
//	        Assertions.assertEquals(events, response.getBody());
//	    }
 
}