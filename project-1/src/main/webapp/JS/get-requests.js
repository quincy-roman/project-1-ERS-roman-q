// //JavaScript to get "Pending" requests.

// async function getPending() {
//     try {
//         const response = await fetch('http://localhost:8080/project-1/getRequests', {
//             method: "POST",

//             //so the idea here is I send a JSON object which returns the relevant requests.
//             body: JSON.stringify({
//                 statusId: 1
//             }),

//             headers: {
//                 Accept: 'application/json',
//                 "Content-type": "application/json; charset=UTF-8"
//             }
//         });

//         const json = await response.json();

//         if(response.ok){
//             let li = `<tr><th>Amount</th><th>Submitted</th><th>Description</th></tr>`;

//             json.array.forEach(pending => {

//                 li += `<tr>
//                 	<td>${pending.amount}</td>
//                 	<td>${pending.submitted}</td>
//                 	<td>${pending.description}</td>
//                 </tr>`;
//             });

//             //Display to the screen.
//             document.getElementById("pending").innerHTML = li;
//         }
//     } catch (e) {
//         return e
//     }
// }

//Let's try AJAX instead.

//Post requests aren't working, Servlet won't receive the data.
// $("#pending").onload(function () {
//     $.post('http://localhost:8080/project-1/getRequests',
//         JSON.stringify({
//             statusId: 1
//         }),

//         function (data, status) {
//             let li = `<tr><th>Amount</th><th>Submitted</th><th>Description</th></tr>`;

//             json.array.forEach(data => {

//                 li += `<tr>
//                 	<td>${data.amount}</td>
//                 	<td>${data.submitted}</td>
//                 	<td>${data.description}</td>
//                 </tr>`;
//             });

//             //Display to the screen.
//             document.getElementById("pending").innerHTML = li;
//         }
//     )
// })

//GET attempt with jQuery -- Doesn't work
// $("#pending").onload(function () {
//     $.get('http://localhost:8080/project-1/getRequests'), function (data, status) {

//         let li = `<tr><th>Amount</th><th>Submitted</th><th>Description</th></tr>`;

//         json.array.forEach(data => {

//             li += `<tr>
//                 	<td>${data.amount}</td>
//                 	<td>${data.submitted}</td>
//                 	<td>${data.description}</td>
//                 </tr>`;
//         });

//         //Display to the screen.
//         document.getElementById("pending").innerHTML = li;
//     }
// })

//GET request with AJAX.
var ourRequest = new XMLHttpRequest();
ourRequest.open('GET', 'http://localhost:8080/project-1/getRequests');
ourRequest.onload = function () {

    var ourData = JSON.parse(ourRequest.responseText);

    let li = `<tr><th>Amount</th><th>Submitted</th><th>Description</th></tr>`;

    json.array.forEach(ourData => {

        li += `<tr>
                	<td>${data.amount}</td>
                	<td>${data.submitted}</td>
                	<td>${data.description}</td>
                </tr>`;
    });

    //Display to the screen.
    document.getElementById("pending").innerHTML = li;

};
ourRequest.send();