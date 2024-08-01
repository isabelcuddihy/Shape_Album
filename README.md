# Shape Image Album

Welcome to the Shape Image Album project! This project is a dynamic photo album application developed as part of coursework at Northeastern University. The application allows users to create and manage albums of shape images, enhancing user engagement and visual appeal through data parsing and layering of shape objects.

## Table of Contents

- [Features](https://www.notion.so/69392d38ebc94206bb2a6af1a292d3b8?pvs=21)
- [Installation](https://www.notion.so/69392d38ebc94206bb2a6af1a292d3b8?pvs=21)
- [Usage](https://www.notion.so/69392d38ebc94206bb2a6af1a292d3b8?pvs=21)
- [Project Structure](https://www.notion.so/69392d38ebc94206bb2a6af1a292d3b8?pvs=21)
- [Screenshots](https://www.notion.so/69392d38ebc94206bb2a6af1a292d3b8?pvs=21)
- [Contributing](https://www.notion.so/69392d38ebc94206bb2a6af1a292d3b8?pvs=21)
- [License](https://www.notion.so/69392d38ebc94206bb2a6af1a292d3b8?pvs=21)

## Features

- **Album Creation:** Create albums to organize shape images.
- **Image Viewing:** View images within an album.
- **Navigation:** Navigate through images in the album.
- **Export:** Export albums to HTML format.

## Installation

To get started with the Shape Image Album, follow these steps:

1. **Clone the repository:**
    
    ```
    shCopy code
    git clone https://github.com/yourusername/shape-image-album.git
    cd shape-image-album
    
    ```
    
2. **Compile the project:**
Ensure you have Java installed on your machine. Navigate to the project directory and run:
    
    ```
    shCopy code
    javac -d bin src/*.java
    
    ```
    
3. **Run the application:**
    
    ```
    shCopy code
    java -cp bin Main
    
    ```
    

## Usage

Once the application is running, you can:

1. **Create a new album:** Follow the prompts to create a new album and add shape images.
2. **View an album:** Open an existing album to view the shape images.
3. **Navigate through images:** Use navigation controls to move through the images in the album.
4. **Export an album:** Export the current album to HTML format for easy sharing and viewing in web browsers.

## Project Structure

The project directory is structured as follows:

```scss
scssCopy code
shape-image-album/
├── bin/
│   └── (compiled Java classes)
├── src/
│   ├── Main.java
│   ├── Album.java
│   ├── ImageViewer.java
│   ├── Exporter.java
│   └── (other source files)
├── images/
│   └── (shape images)
├── README.md
└── LICENSE

```

## Technologies Used

- Java
- Swing
- JUnit
- HTML/CSS
- SVG

## Development Details

This project was developed at Northeastern University for my Java Course in Object Oriented Programming and follows the Model-View-Controller (MVC) architecture. It adheres to Object-Oriented Programming (OOP) principles, ensuring optimized, maintainable, and flexible code. The dynamic photo album application parses data and layers shape objects to generate images, providing an engaging user experience.

## Contributing

Contributions are welcome! If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are encouraged.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a pull request

## License

This project is licensed under the MIT License. See the LICENSE file for details.
