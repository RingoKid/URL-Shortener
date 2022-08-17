package com.example.demo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "urls")
public class Url {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public Integer id;

    @Column(name = "short_url")
    public String shortUrl;

    @Column(name = "long_url")
    public String longUrl;

    public Url() {
    }
}
