## How to test manually

## List of controllers methods
| #   | Controller           | CRUD | Address                           | Description                                                                                       |
|-----|----------------------|------|-----------------------------------|---------------------------------------------------------------------------------------------------|
| 1   | Financial Operations | POST | /financial-operations/add/all     | This method adds financial transactions in rubles (specify kopecks in amount field)               |
| 2   | Currencies           | GET  | /currencies/upload                | This method uploads the exchange rates for USD and EUR                                            |
| 3   | Financial Operations | POST | /financial-operations/recalculate | This method recalculates financial transactions in the selected currency for the specified period |

## Method number 1

Input data - option 1
```
[{}]
```

Input data - option 2
```
[{},{}]
```

Input data - option 3
```
[
  {
    "amount": -1
  }
]
```

Input data - option 4
```
[
  {
    "amount": 0
  }
]
```

Input data - option 5
```
[
  {
    "amount": 1
  }
]
```

Input data - option 6
```
[
  {
    "date": "2023-11-12"
  }
]
```

Input data - option 7
```
[
  {
    "description": "string"
  }
]
```

Input data - option 8
```
[
  {
    "amount": -1,
    "date": "2023-11-12"
  }
]
```

Input data - option 9
```
[
  {
    "amount": 0,
    "date": "2023-11-12"
  }
]
```

Input data - option 10
```
[
  {
    "amount": 1,
    "date": "2023-11-12"
  }
]
```

Input data - option 11
```
[
  {
    "amount": -1,
    "description": "string"
  }
]
```

Input data - option 12
```
[
  {
    "amount": 0,
    "description": "string"
  }
]
```

Input data - option 13
```
[
  {
    "amount": 1,
    "description": "string"
  }
]
```

Input data - option 14
```
[
  {
    "date": "2023-11-12",
    "description": "string"
  }
]
```

Input data - option 15
```
[
  {
    "amount": -1,
    "date": "2023-11-12",
    "description": "string"
  }
]
```


Input data - option 16
```
[
  {
    "amount": 0,
    "date": "2023-11-12",
    "description": "string"
  }
]
```

Input data - option 17
```
[
  {
    "amount": 10,
    "date": "2023-11-12",
    "description": "string"
  }
]
```

Result of a successful request (option 17)
```
[
  {
    "id": 1000,
    "date": "2023-11-12",
    "description": "string",
    "amount": 10
  }
]
```

Input data - option 18
```
[
  {
    "amount": 20,
    "date": "2023-11-12",
    "description": "string"
  },
  {
    "amount": 30,
    "date": "2023-11-12",
    "description": "string"
  }
]
```

Result of a successful request (option 18)
```
[
  {
    "id": 1001,
    "date": "2023-11-12",
    "description": "string",
    "amount": 20
  },
  {
    "id": 1002,
    "date": "2023-11-12",
    "description": "string",
    "amount": 30
  }
]
```

## Method number 2

To test this method, simply click the 'Execute' button and compare the received answer with the provided option below

Result of a successful request:
```
[
  {
    "vname": "Доллар США",
    "vnom": 1,
    "vcurs": 92.0535,
    "vcode": 840,
    "vchCode": "USD",
    "vunitRate": 92.0535
  },
  {
  "vname": "Евро",
  "vnom": 1,
  "vcurs": 98.3155,
  "vcode": 978,
  "vchCode": "EUR",
  "vunitRate": 98.3155
  }
]
```

## Method number 3

Input data - option 1
```
{}
```

Input data - option 2
```
{
  "dateFrom": "2023-11-01"
}
```

Input data - option 3
```
{
  "dateTo": "2023-11-05"
}
```

Input data - option 4
```
{
  "dateFrom": "2023-11-01",
  "dateTo": "2023-11-05"
}
```

Input data - option 5
```
{
  "dateFrom": "2023-11-01",
  "vchCode": "EUR"
}
```

Input data - option 6
```
{
  "dateTo": "2023-11-05",
  "vchCode": "EUR"
}
```

Input data - option 7
```
{
  "dateFrom": "2023-11-01",
  "vchCode": "GBP"
}
```

Input data - option 8
```
{
  "dateTo": "2023-11-05",
  "vchCode": "GBP"
}
```

Input data - option 9
```
{
  "vchCode": "EUR"
}
```

Input data - option 10
```
{
  "vchCode": "GBP"
}
```

Input data - option 11
```
{
  "dateFrom": "2023-11-01",
  "dateTo": "2023-11-05",
  "vchCode": "EUR"
}
```

Result of a successful request (option 11)
```
[
  {
    "id": 1,
    "date": "2023-11-01",
    "description": "Income",
    "amount": 12.19
  },
  {
    "id": 2,
    "date": "2023-11-01",
    "description": "Income",
    "amount": 8.13
  },
  {
    "id": 3,
    "date": "2023-11-01",
    "description": "Income",
    "amount": 35.57
  },
  {
    "id": 4,
    "date": "2023-11-03",
    "description": "Income",
    "amount": 142.27
  },
  {
    "id": 5,
    "date": "2023-11-03",
    "description": "Income",
    "amount": 659.5
  },
  {
    "id": 6,
    "date": "2023-11-05",
    "description": "Income",
    "amount": 12.19
  }
]
```

Input data - option 12
```
{
  "dateFrom": "2023-11-01",
  "dateTo": "2023-11-05",
  "vchCode": "USD"
}
```

Result of a successful request (option 12)
```
[
  {
    "id": 1,
    "date": "2023-11-01",
    "description": "Income",
    "amount": 12.19
  },
  {
    "id": 2,
    "date": "2023-11-01",
    "description": "Income",
    "amount": 8.13
  },
  {
    "id": 3,
    "date": "2023-11-01",
    "description": "Income",
    "amount": 35.57
  },
  {
    "id": 4,
    "date": "2023-11-03",
    "description": "Income",
    "amount": 142.27
  },
  {
    "id": 5,
    "date": "2023-11-03",
    "description": "Income",
    "amount": 659.5
  },
  {
    "id": 6,
    "date": "2023-11-05",
    "description": "Income",
    "amount": 12.19
  }
]
```

## Contacts
| Name     | Phone + WhatsApp | Telegram           | Email                 |
|----------|------------------|--------------------|-----------------------|
| Grigoriy | 8-924-116-18-34  | posmitniy_grigoriy | pga.profile@gmail.com |
