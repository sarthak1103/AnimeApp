# AnimeApp

An app that displays a list for top rated animes and see their details

## Features
- List of top rated animes in grid view along with poster.
- See details of animes like plot/synopsis, genre(s), title, ratings, score, etc.
- Play the youtube trailer of animes.

## Limitations

- The details are fetched from the https://api.jika.moe/v4/anime/{id}, but there is no main cast details available. So skipped the cast details.
- The home page doesn't have pagination so data is fetched only once.
