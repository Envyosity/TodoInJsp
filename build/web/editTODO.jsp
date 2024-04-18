<%@page import="model.Todo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html">
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>Edit TODO</title>
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
        <form action="editTODOdb" method="post">
            <div>
                <input type="hidden" name="id" id="hiddenField" value="${todo.id}"/>
                <label class="form-label">Name:</label>
                <input
                    style="width: 25%"
                    type="text"
                    class="form-control"
                    name="name"
                    required
                    value="${todo.name}"
                    />  
                <label class="form-label">isDone?</label>
                <label class="switch">
                    <input type="checkbox" name="isDone" ${todo.isDone == 1 ? 'checked' : ''}>
                    <span class="slider round"></span>
                </label>

            </div>
            <button style="margin-top: 5px" type="submit" class="btn btn-primary">Edit TODO</button>
        </form>            
        <script 
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"
        ></script>
    </body>
</html>