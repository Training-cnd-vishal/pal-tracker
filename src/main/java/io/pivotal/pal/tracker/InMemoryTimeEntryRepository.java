package io.pivotal.pal.tracker;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> entriesMap = new HashMap<>();
    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;
        TimeEntry  nwTimeEntry = new TimeEntry(id,timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(),timeEntry.getHours());
        entriesMap.put(id,nwTimeEntry);
        return nwTimeEntry;
    }

    @Override
    public void delete(long timeEntryId) {
        entriesMap.remove(timeEntryId);
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return entriesMap.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(entriesMap.values());
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        if (find(timeEntryId)== null)
            return null;

        TimeEntry  updateTimeEntry = new TimeEntry(timeEntryId,timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(),timeEntry.getHours());
        entriesMap.replace(timeEntryId,updateTimeEntry);
        return updateTimeEntry;
    }
}