import React, {Component} from "react";
import {StyleSheet, Text, View, Image, FlatList} from "react-native";
import BookItem from "./BookItem"

const mockBooks = [
    {
        rank: 1,
        title: "GATHERING PREY",
        author: "John Sandford",
        book_image: "http://du.ec2.nytimes.com.s3.amazonaws.com/prd/books/9780399168796.jpg"
    },
    {
        rank: 2,
        title: "MEMORY MAN",
        author: "David Baldacci",
        book_image: "http://du.ec2.nytimes.com.s3.amazonaws.com/prd/books/9781455586387.jpg"
    }
];

class BookList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: this._addKeysToBooks(mockBooks)
        }
    }

    _renderItem = ({item}) => {
        return (
            <BookItem
                coverURL={item.book_image}
                title={item.title}
                author={item.author}
            />
        )
    };

    _addKeysToBooks = books => {
        return books.map(book => {
            return Object.assign(book, {key: book.title});
        })
    };

    render() {
        return <FlatList data={this.state.data} renderItem={this._renderItem}/>;
    }
}

const style = StyleSheet.create({
    container: {flex: 1, paddingTop: 22}
});

export default BookList;