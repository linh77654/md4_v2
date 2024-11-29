$(document).ready(function() {
    $('#searchForm').on('submit', function(e) {
        e.preventDefault();

        let query = $('#search').val();

        $.ajax({
            url: 'http://localhost:8080/api/blogs/search',
            method: 'GET',
            data: { search: query },
            dataType: 'json',
            success: function(data) {
                console.log(data);

                if (data.length === 0) {
                    $('#results tbody').html('<p>Không tìm thấy kết quả nào.</p>');
                } else {
                    let resultsHtml = `
                        <table class="table table-striped">
                            <tbody>`;

                    for (let i = 0; i < data.length; i++) {
                        let blog = data[i];
                        resultsHtml += `
                            <tr>
                                <td>${i + 1}</td>
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

                    resultsHtml += `
                            </tbody>
                        </table>`;

                    $('#results tbody').html(resultsHtml);
                }
            },
            error: function(xhr, status, error) {
                console.error('Có lỗi xảy ra: ', error);
                $('#results').html('<p>Đã xảy ra lỗi khi tìm kiếm.</p>');
            }
        });
    });
});
