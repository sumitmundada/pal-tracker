package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository repository) {
        this.timeEntryRepository = repository;
    }

    @PostMapping
    public ResponseEntity  create (@RequestBody TimeEntry timeEntryToCreate){
        TimeEntry newEntry = timeEntryRepository.create(timeEntryToCreate);
        // Returning a ResponseEntity allows us to control the resulting HTTP status code
        return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
    }

    // Retrieve a single record
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if (timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    };

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry){
        TimeEntry updatedTimeEntry = timeEntryRepository.update(id, timeEntry);
        if (updatedTimeEntry != null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id){
        timeEntryRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
