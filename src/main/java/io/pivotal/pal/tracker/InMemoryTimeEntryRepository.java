package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    // Store entries in a HashMap for onw
    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();
    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;

        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntries.put(id, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(timeEntries.get(id) != null) {
            TimeEntry updatedEntry = new TimeEntry(
                    id,
                    timeEntry.getProjectId(),
                    timeEntry.getUserId(),
                    timeEntry.getDate(),
                    timeEntry.getHours()
            );

            timeEntries.replace(id, updatedEntry);
            return updatedEntry;
        }
        return null;
    }

    @Override
    public void delete(long id) {
        timeEntries.remove(id);
    }
}
