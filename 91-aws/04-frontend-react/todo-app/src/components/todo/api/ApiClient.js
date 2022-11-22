import axios from 'axios'

export const apiClient = axios.create(
    {
        baseURL: 'http://localhost:5000' //#CHANGE
        //baseURL: 'http://03restapifullstackh2-env.eba-tmxhn3ah.ap-south-1.elasticbeanstalk.com/'
    }
);

/* For Best Practices https://facebook.github.io/create-react-app/docs/adding-custom-environment-variables*/
