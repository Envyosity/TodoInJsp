<%@page import="dao.TodoDAO,model.Todo,java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TODO List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-danger">
            <div class="container-fluid">
                <a class="navbar-brand" href="/projeto">TODO</a>
                <button
                    class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                    >
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="addTODO">Add TODO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="listTODO">TODO List</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <table class="table table-striped-columns" width="90%">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>isDone?</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${lista}" var="u">
                <tr>
                    <td>${u.getId()}</td>
                    <td>${u.getName()}</td>
                    <td>
                        <label class="switch">
                            <input type="checkbox" disabled ${u.getIsDone() == 1 ? 'checked' : ''}>
                            <span class="slider round"></span>
                        </label>
                    </td>
                    <td>
                        <form action="editTODO" method="post">
                            <input type="hidden" name="id" id="hiddenField" value="${u.getId()}"/>
                            <button type="submit" value="Editar">
                                <box-icon name='pencil' color='#000000' ></box-icon>
                            </button>
                        </form>
                    </td>
                    <td>
                        <button onclick="deleteConfirmation(${u.getId()})">
                            <box-icon name='trash' color="#FF0000"></box-icon>
                        </button>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"
        ></script>
        <script 
            src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js">
        </script>
        <script>
            function deleteConfirmation(id) {
                let resposta = confirm("Confirma a exclusao deste contato ?");
                if (resposta === true) {
                    //window.location.href = "excluiContato";
                    post('/projeto/deleteTODO', {id: id});
                } else {
                    window.location.href = "/error"
                }
            }
            function post(path, params, method = 'post') {
                const form = document.createElement('form');
                form.method = method;
                form.action = path;

                for (const key in params) {
                    if (params.hasOwnProperty(key)) {
                        const hiddenField = document.createElement('input');
                        hiddenField.type = 'hidden';
                        hiddenField.name = key;
                        hiddenField.value = params[key];

                        form.appendChild(hiddenField);
                    }
                }

                document.body.appendChild(form);
                form.submit();
            }
            function edit(id) {

                post('/projeto/editTODO', {id: id});

            }

        </script>
    </body>
</html>