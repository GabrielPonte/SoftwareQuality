import axios from 'axios';
import { BuscarPostoSaudeContext } from './buscar-posto-saude.model';

const baseApiUrl = 'api/agendamento-process/buscar-posto-saude';

export default class BuscarPostoSaudeService {
  public loadContext(taskId: number): Promise<BuscarPostoSaudeContext> {
    return new Promise<BuscarPostoSaudeContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<BuscarPostoSaudeContext> {
    return new Promise<BuscarPostoSaudeContext>((resolve, reject) => {
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

  public complete(buscarPostoSaudeContext: BuscarPostoSaudeContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, buscarPostoSaudeContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
