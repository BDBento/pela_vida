package com.pela_vida.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.nio.file.Paths;
import java.nio.file.Path;

@Path("/")
public class FrontendResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Path index() {
        return Paths.get("src/main/resources/META-INF/resources/index.html");
    }
}