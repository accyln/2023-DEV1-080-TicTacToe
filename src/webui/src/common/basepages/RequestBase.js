export function PostSecure(action,body)
{
    var header = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
       };

   return fetch("http://localhost:8080/"+ action, {
    method: 'POST',
    headers: header,
    body: JSON.stringify(body)
});
}


export async function GetSecure(action) {
    try{
     
      return await fetch("http://localhost:8080/" + action, {
        method: 'GET'
    });
    }
    catch(err) {
      throw err;
    }
  }

