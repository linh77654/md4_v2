$(document).ready(function() {
    let currentPage = 1;
    const pageSize = 3;

    function loadPosts(page) {
        $.ajax({
            url: 'http://localhost:8080/api/blogs/page?page=' + page + '&size=' + pageSize,
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                console.log(data);

                if (page === 1) {
                    $('#results tbody').empty();
                }

                if (data.length === 0 && page === 1) {
                    $('#results tbody').html('<tr><td colspan="9">Không có bài viết nào.</td></tr>');
                    $('#loadMore').hide();
                    return;
                } else if (data.length === 0) {
                    $('#loadMore').text('Không còn bài viết nào để tải thêm').prop('disabled', true);
                    return;
                }

                let postsHtml = '';

                for (let i = 0; i < data.length; i++) {
                    let blog = data[i];
                    postsHtml += `
                        <tr>
                            <td>${(currentPage - 1) * pageSize + i + 1}</td>
                            <td>${blog.title}</td>
                            <td>${blog.content}</td>
                            <td>${blog.author}</td>
                            <td>${blog.category.name}</td>
                            <td>${blog.createDate}</td>
                            <td><a class="btn btn-warning" href="blog/${blog.id}/edit">Edit</a></td>
                            <td><a class="btn btn-danger" href="blog/${blog.id}/delete">Delete</a></td>
                            <td><a class="btn btn-info" href="blog/${blog.id}/view">Blog Detail</a></td>
                        </tr>`;
                }

                $('#results tbody').append(postsHtml);
            },
            error: function(xhr, status, error) {
                console.error('Có lỗi xảy ra: ', error);
                $('#results tbody').append('<tr><td colspan="9">Đã xảy ra lỗi khi tải thêm bài viết.</td></tr>');
            }
        });
    }

    loadPosts(currentPage);

    $('#loadMore').on('click', function() {
        currentPage++;
        loadPosts(currentPage);
    });
});
