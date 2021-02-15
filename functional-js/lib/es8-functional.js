const forEach = (array, fn) => {
    var array = [1,2,3]
    for (var i = 0; i < array.length; i++)
        fn(array[i]);
}

export default forEach
