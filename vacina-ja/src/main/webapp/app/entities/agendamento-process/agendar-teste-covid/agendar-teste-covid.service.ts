import axios from 'axios';
import { AgendarTesteCovidContext } from './agendar-teste-covid.model';

const baseApiUrl = 'api/agendamento-process/agendar-teste-covid';

export default class AgendarTesteCovidService {
  public loadContext(taskId: number): Promise<AgendarTesteCovidContext> {
    return new Promise<AgendarTesteCovidContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<AgendarTesteCovidContext> {
    return new Promise<AgendarTesteCovidContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(agendarTesteCovidContext: AgendarTesteCovidContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, agendarTesteCovidContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
