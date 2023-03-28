import { Routes, Route }  from 'react-router-dom';
import { Layout }         from './components/layout/Layout';
import { UrlForm }        from './components/url/url-form/UrlForm';
import { TextForm }       from './components/text/text-form/TextForm';

import './App.css';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path = "/" element = {<Layout />} >
          <Route path = "/" element = {<TextForm />} />
          <Route path = "count/url" element = {<UrlForm />} />
        </Route>
      </Routes>

    </div>
  );
}

export default App;
