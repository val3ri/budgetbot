package group.budgetbot.controller;


import group.budgetbot.exception.ResourceNotFoundException;
import group.budgetbot.model.Entry;
import group.budgetbot.repository.EntryRepository;
import group.budgetbot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/entries/{userId}/entries")
    public List<Entry> getEntriesByUserId(@PathVariable Long userId) {
        return entryRepository.findByUserId(userId);
    }

    @PostMapping("/entries/{userId}/entries")
    public Entry addEntry(@PathVariable Long userId,
                          @Valid @RequestBody Entry entry) {
        return userRepository.findById(userId)
                .map(user -> {
                    entry.setUser(user);
                    return entryRepository.save(entry);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @PutMapping("/entries/{userId}/entries/{entryId}")
    public Entry updateEntry(@PathVariable Long userId,
                             @PathVariable Long entryId,
                             @Valid @RequestBody Entry entryRequest) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }

        return entryRepository.findById(entryId)
                .map(entry -> {
                    entry.setValue(entryRequest.getValue());
                    entry.setLabel(entryRequest.getLabel());
                    return entryRepository.save(entry);
                }).orElseThrow(() -> new ResourceNotFoundException("Entry not found with id " + entryId));
    }

    @DeleteMapping("/entries/{userId}/entries/{entryId}")
    public ResponseEntity<?> deleteEntry(@PathVariable Long userId,
                                         @PathVariable Long entryId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }

        return entryRepository.findById(entryId)
                .map(entry -> {
                    entryRepository.delete(entry);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Entry not found with id " + entryId));

    }
}
