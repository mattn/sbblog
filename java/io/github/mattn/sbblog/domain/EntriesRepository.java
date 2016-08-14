package io.github.mattn.sbblog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntriesRepository extends JpaRepository<Entry, Integer> {
}
