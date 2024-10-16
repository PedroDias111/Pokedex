
# Pokedex - Your Pokémon Companion

**Pokedex** is an Android app that I built to learn about modern Android development technologies. This app allows users to explore detailed information about Pokémon from the popular series. I decided to create this app using the free PokéAPI to improve my skills in using various technologies while providing a user-friendly interface to discover Pokémon stats, abilities, and much more!

## Features

- **Detailed Stats**: View Pokémon stats like Attack, Defense, and Speed with animated progress bars.
- **Abilities & Species Info**: Learn about Pokémon's abilities, types, and species details.
- **Pokémon Cries**: Play Pokémon cries directly from the app.
- **Pagination**: Efficiently browse through a large number of Pokémon with pagination support.

## Technologies Used

- **Retrofit**: For networking and API calls to fetch Pokémon data.
- **Room**: For local database management and caching Pokémon details.
- **Dagger Hilt**: For dependency injection to manage app components and services.
- **Jetpack Compose**: For building modern UI with a declarative approach.
- **Pagination**: To load Pokémon data in chunks, improving performance and user experience.

## How It Works

1. **Browse Pokémon**: Browse through Pokémon fetched from the PokéAPI.
2. **View Stats**: Check out stats and abilities.
3. **Play Pokémon Cries**: Listen to a Pokémon’s unique cry.

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/PedroDias111/Pokedex.git
    ```

2. Open the project in **Android Studio**.
3. Build and run the project on a device or emulator.

## Screenshots

<div style="display: flex; justify-content: space-around;">

<img src="https://github.com/user-attachments/assets/eef63d29-9be4-4251-8494-84653d183076" alt="Pokédex Screenshot" width="300" />

<img src="https://github.com/user-attachments/assets/bb0d7caf-d4d3-48ef-825e-a956ce27f7fd" alt="Pokédex Screenshot" width="300" />

</div>

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
