
<h1>Introduction</h1>

This is my REST api backend application for working with To-Do list applicattions.


<h2> Links to work with API</h2>


<h3>Before using API</h3>

#### POST register user

```http
  POST /api/auth/register
```

| Parameter   | Type     | Description                    |
|:------------|:---------|:-------------------------------|
| `firstname` | `string` | **Required**. User's firstname |
| `lastname`  | `string` | **Required**. User's lastname  |
| `email`     | `string` | **Required**. User's email     |
| `password`  | `string` | **Required**. User's password  |

#### POST authenticate user

```http
  POST /api/auth/authenticate
```

| Parameter   | Type     | Description                    |
|:------------|:---------|:-------------------------------|
| `email`     | `string` | **Required**. User's email     |
| `password`  | `string` | **Required**. User's password  |



<h3>Links for boards</h3>

#### GET all boards

```http
  GET /api/boards
```

#### GET board by UUID

```http
  GET /api/boards/{UUID}
```

#### GET all tasks by board's UUID

```http
  GET /api/boards/{UUID}/tasks
```

#### POST new board

```http
  POST /api/boards/create
```
| Parameter | Type     | Description                |
|:----------|:---------|:---------------------------|
| `name`    | `string` | **Required**. Board's name |

#### UPDATE board by UUID

```http
  PUT /api/boards/{UUID}/update
```

| Parameter | Type     | Description                    |
|:----------|:---------|:-------------------------------|
| `name`    | `string` | **Required**. Board's new name |

#### DELETE board by UUID

```http
  DELETE /api/boards/{UUID}/delete
```

<h3>Links for tasks</h3>

#### GET all tasks

```http
  GET /api/tasks
```

#### GET task by UUID

```http
  GET /api/tasks/{UUID}
```

#### POST new task

```http
  POST /api/tasks/create
```
| Parameter    | Type     | Description                |
|:-------------|:---------|:---------------------------|
| `name`       | `string` | **Required**. Task's name  |
| `board_UUID` | `string` | **Required**. Board's UUID |

#### UPDATE task by UUID

```http
  PUT /api/tasks/{UUID}/update
```

| Parameter | Type     | Description                   |
|:----------|:---------|:------------------------------|
| `name`    | `string` | **Required**. Task's new name |

#### DELETE task by UUID

```http
  DELETE /api/tasks/{UUID}/delete
```
