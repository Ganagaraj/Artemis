
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min'
import './home.css'
function Home(){
    return(
<>
<div class="container-fuild">
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <span class="navbar-brand mb-0 h1" id="title">Artemis Data</span>
  </nav>
  
</div>

<div class="table-responsive">
  <table class="table table-striped table-hover">
  <thead>
    <tr>
      <th scope="col">S.NO</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Mahi</td>
      <td >Larry the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
  </table>
</div>
</>
    )
}

export default Home;