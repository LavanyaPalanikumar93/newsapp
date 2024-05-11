const FetchBookmarks = async () => {
  const url = 'http://localhost:8080/api/bookmarks';
  //INSERT TOKEN HERE BUT DONT FORGET TO REMOVE IT BEFORE MERGING

  try {
    const token = localStorage.getItem('token');

    const response = await fetch(url, {
      method: 'GET',
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    const data = await response.json();
    return data; // Return the fetched data
  } catch (error) {
    console.error('There was a problem with your fetch operation:', error);
    throw error;
  }
};

export default FetchBookmarks;
