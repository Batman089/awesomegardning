package com.company.project.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;

import java.net.URI;
import java.text.MessageFormat;
import java.util.Map;
import java.util.UUID;

public class ObjectNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    private static final URI TYPE = URI.create("https://zalando.github.io/problem/constraint-violation");

    private static final String PATTERN = "{1} wih id {0} not found";

    public ObjectNotFoundException(UUID id, Class<?> cls) {
        this(id, cls, null);
    }

    public ObjectNotFoundException(UUID id, Class<?> cls, ThrowableProblem cause) {
        super(
            TYPE,
            "Object Not Found",
            Status.NOT_FOUND,
            MessageFormat.format(PATTERN, id, cls.getSimpleName()),
            null,
            cause,
            Map.of("objectId", id, "objectType", cls.getSimpleName())
        );
    }
}
