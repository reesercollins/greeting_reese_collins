package com.example.restservice;

import org.springframework.data.history.RevisionMetadata.RevisionType;

public record GreetingRevision(Greeting greeting, Number revision, RevisionType revisionType) {
}
